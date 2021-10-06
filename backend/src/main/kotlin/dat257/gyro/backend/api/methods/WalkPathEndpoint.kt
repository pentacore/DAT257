package dat257.gyro.backend.api.methods

import dat257.gyro.backend.database.DatabaseClient.Companion.loggedTransaction
import dat257.gyro.backend.database.models.WalkPath
import dat257.gyro.backend.database.models.WalkPathClassification
import dat257.gyro.backend.database.models.WalkPathNode
import dat257.gyro.backend.database.tables.WalkPaths
import dat257.gyro.backend.helpers.EndpointHelper
import dat257.gyro.shared.dataTypes.ApiError
import dat257.gyro.shared.enums.ApiErrorCode
import dat257.gyro.shared.enums.AreaType
import dat257.gyro.shared.enums.PathType
import dat257.gyro.shared.exceptions.ApiException
import dat257.gyro.shared.exceptions.RequiredDataMissingException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import dat257.gyro.shared.dataTypes.WalkPath as SWalkPath

object WalkPathEndpoint : EndpointHelper() {
    fun get(req: Request): Response {
        val user = try {
            getProfileFromRequest(req)
        } catch (e: NoSuchElementException) {
            return Response(Status.UNAUTHORIZED)
        }
        val response: Response = Response(Status.OK)
        try {
            val model = Json.decodeFromString<SWalkPath>(req.bodyString())
            return if (model.id == null) {
                val public = loggedTransaction {
                    val t = WalkPath.find( Op.build { WalkPaths.public eq true } and Op.build { WalkPaths.profile neq user.id } ).toList()
                    val r = emptyList<SWalkPath>().toMutableList()
                    for (wp in t) {
                        r.add(wp.toSharedModel())
                    }
                    r.toList()
                }
                val userPaths = user.getWalkPathsSharedModel()
                val paths = HashMap<String,List<SWalkPath>>()
                paths["public"] = public
                paths["user"] = userPaths
                response.body(Json.encodeToString(paths))
            } else {
                val path = loggedTransaction {
                    WalkPath.find(
                        Op.build { WalkPaths.id eq model.id } and (WalkPaths.public or Op.build { WalkPaths.profile eq user.id })
                    ).limit(1).first()
                }

                response.body(Json.encodeToString(path))
            }
        } catch (e: NoSuchElementException) {
            return Response(Status.NOT_FOUND).body(Json.encodeToString(ApiError(ApiErrorCode.TargetNotFound, "Could not find any WalkPath with that id")))
        } catch (e: SerializationException) {

            return Response(Status.UNPROCESSABLE_ENTITY)
        }
    }

    //TODO Make a method to handle this in the db model?
    fun put(req: Request): Response {
        val user = try {
            getProfileFromRequest(req)
        } catch (e: NoSuchElementException) {
            return Response(Status.UNAUTHORIZED)
        }

        val p = try {
            val model = Json.decodeFromString<SWalkPath>(req.bodyString())

            loggedTransaction {
                val path = WalkPath.new {
                    this.public = model.public ?: false
                    this.name = model.name ?: throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "missing required field: name"))
                    this.description = model.description ?: ""
                    this.profile = user
                }

                WalkPathClassification.new {
                    this.areaType = model.classification?.areaType ?: AreaType.NONE
                    this.pathType = model.classification?.pathType ?: PathType.NONE
                    this.handicapFriendly = model.classification?.handicapFriendly ?: false
                    this.strollerFriendly = model.classification?.strollerFriendly ?: false
                    this.lighted = model.classification?.lighted ?: false
                    this.walkPath = path
                    this.createdAt = LocalDateTime.now()
                    this.updatedAt = LocalDateTime.now()
                }

                for (node in model.nodes ?: throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "missing required field: nodes"))) {
                    WalkPathNode.new {
                        this.walkPath = path
                        this.longitude = node.longitude ?: throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "missing required field: node.longitude"))
                        this.latitude = node.latitude ?: throw RequiredDataMissingException(ApiError(ApiErrorCode.MissingRequiredData, "missing required field: node.latitude"))
                    }
                }
                path
            }
        } catch (e: ApiException) {
            return Response(Status.BAD_REQUEST).body(Json.encodeToString(e.apiError))
        } catch (e: SerializationException) {
            return Response(Status.UNPROCESSABLE_ENTITY)
        }

        return Response(Status.OK).body(Json.encodeToString(p.toSharedModel()))
    }

    fun patch(req: Request): Response {
        val model = Json.decodeFromString<SWalkPath>(req.bodyString())
        TODO()
    }

    fun delete(req: Request): Response {
        val model = Json.decodeFromString<SWalkPath>(req.bodyString())
        TODO()
    }

    fun routes(): RoutingHttpHandler {

        return org.http4k.routing.routes(
            Method.GET to { req -> get(req) },
            Method.PUT to { req -> put(req) },
            Method.PATCH to { req -> patch(req) },
            Method.DELETE to { req -> delete(req) }
        )

    }
}
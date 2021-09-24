package dat257.gyro.models

import dat257.gyro.data.Coordinate
import dat257.gyro.data.CoordinateRecord
import java.time.Clock

/**
 * model for handling coordinates
 */
class Locator(val current: Coordinate,val passed: CoordinateRecord,val clock: Clock): Subsciber{
    init {

    }

    public fun update():Locator = TODO()


}
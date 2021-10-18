package dat257.gyro.factory.repositoryFactory


/**
 * TODO
 * create factory logic for *repositories,
 *                          *viewmodels
 *                          *dataSources
 */
abstract class RepositoryFactory {
    abstract fun createReposity(): Repository
}

class RemoteRepositoryFactory() : RemoteRepository
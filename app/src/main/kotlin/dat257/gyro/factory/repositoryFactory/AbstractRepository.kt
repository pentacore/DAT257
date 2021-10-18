package dat257.gyro.factory.repositoryFactory

import dat257.gyro.model.BaseApiResponse

// todo i framtiden för att enkelt skapa repos snyggt?
// https://narbase.com/2020/06/07/design-patterns-abstract-factory-with-kotlin-examples/
// https://narbase.com/2020/06/15/design-patterns-prototype-pattern-with-kotlin-examples/
abstract class AbstractRepository constructor(
    dataSource : DataSource
): BaseApiResponse() {
}
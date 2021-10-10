package model

import java.math.BigInteger

sealed class Unit (magnitude : BigInteger) {


    sealed class Distance(magnitude: BigInteger) : Unit(magnitude) {

        //Metric

        sealed class Metric(magnitude: BigInteger) : Distance(magnitude) {
            class Metres(magnitude: BigInteger) : Metric(magnitude) {

            }

            class Kilometres(magnitude: BigInteger) : Metric(magnitude) {

            }

            class Miles(magnitude: BigInteger) : Metric(magnitude) {

            }
        }

        //Imperial
        //yard = 0.9114 metres
        //mile = 1609.344 metres

        sealed class Imperial(magnitude: BigInteger) : Distance(magnitude) {
            class Yard(magnitude: BigInteger) : Imperial(magnitude) {

            }

            class Mile(magnitude: BigInteger) : Imperial(magnitude) {

            }
        }
    }

    sealed class Time(magnitude: BigInteger){
        sealed class Hour(magnitude: BigInteger){

        }
        sealed class Minute(magnitude: BigInteger){

        }
        sealed class Second(magnitude: BigInteger){

        }

    }
}
package model

import android.graphics.Paint

sealed class Settings() {
    sealed class LineSettings(identity: LineIdentity) {
        sealed class Color(c: Paint) {

        }

        sealed class Shape(s: LineShape) {

        }

        sealed class Width(w: model.Width) {

        }
    }
}

enum class LineShape {
    Dashed,
    Filled
}

enum class LineIdentity {
    PreRecorded,
    Current
}

enum class Width {
    Small,
    Medium,
    Large
}
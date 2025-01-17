package top.fifthlight.touchcontroller.gal

import java.nio.file.Path

interface NativeLibraryPathGetter {
    fun getNativeLibraryPath(containerName: String, containerPath: String, debugPath: Path?): Path?
}
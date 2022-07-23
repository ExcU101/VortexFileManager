package io.github.excu101.filesystem.unix.operation

import io.github.excu101.filesystem.IdRegister
import io.github.excu101.filesystem.Observer
import io.github.excu101.filesystem.fs.operation.FileOperation
import io.github.excu101.filesystem.fs.path.Path
import io.github.excu101.filesystem.unix.UnixCalls

class UnixRenameOperation(
    private val source: Path,
    private val dest: Path,
    private val observer: Observer<Path>? = null
) : FileOperation() {

    override val id: Int = IdRegister.register(IdRegister.Type.OPERATION)

    override suspend fun perform() {
        try {
            UnixCalls.rename(source.bytes, dest.bytes)
        } catch (exception: Exception) {
            observer?.onError(exception)
            return
        }
        observer?.onComplete()
    }


}
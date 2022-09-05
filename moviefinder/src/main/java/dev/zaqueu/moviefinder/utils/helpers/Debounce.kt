package dev.zaqueu.moviefinder.utils.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    debounceTime: Long = 300L,
    scope: CoroutineScope,
    cb: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(debounceTime)
            cb(param)
        }
    }
}

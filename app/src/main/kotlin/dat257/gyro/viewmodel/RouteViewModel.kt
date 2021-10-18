package dat257.gyro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dat257.gyro.data.Repository
import dat257.gyro.data.remote.HttpResponse
import dat257.gyro.model.Route
import dat257.gyro.utils.NetworkResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltViewModel
class RouteViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    //  Routes
    private val _putRouteResponse: MutableLiveData<NetworkResult<HttpResponse>> = MutableLiveData()
    val putRouteResponse: LiveData<NetworkResult<HttpResponse>> = _putRouteResponse

    private val _getRouteResponse: MutableLiveData<NetworkResult<Route>> = MutableLiveData()

    val getRouteResponse: MutableLiveData<NetworkResult<Route>> = _getRouteResponse


    fun fetchRoute() = viewModelScope.launch {
        repository.getRoute().collect { value ->
            _getRouteResponse.value = value
        }
    }


    fun saveRoute() = viewModelScope.launch {
        repository.getRoute().collect { value ->
            _getRouteResponse.value = value
        }
    }
}
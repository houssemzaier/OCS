package com.orange.core.network.data_source

import com.google.common.truth.Truth.assertThat
import com.orange.core.network.di.NetworkHiltModule
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkApiTest {

    @Test
    fun `check searchContents call to the webservice`() = runTest {
        val networkApi = createNetworkApi()

        val searchContents = networkApi.searchContents("title=rouge")

        assertThat(searchContents.template).isEqualTo("search")
    }

    @Test
    fun `check getProgramDetails call to the webservice`() = runTest {
        val networkApi = createNetworkApi()

        val searchContents = networkApi.getProgramDetails("AMOURXXXXXXW0078049")

        assertThat(searchContents.template).isEqualTo("detail")
    }

    private fun createNetworkApi(): NetworkApi = NetworkHiltModule.providesNetworkApi(
        NetworkHiltModule.providesRetrofit(
            okHttpClient = NetworkHiltModule.providesOkHttpClient(),
            json = NetworkHiltModule.providesJson(),
        ),
    )
}

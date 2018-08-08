package com.parthdave93.di

import android.content.Context
import com.parthdave93.tvdemo.util.Constants
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * Created by Parth Dave on 02-07-2018.
 */
class NetworkMockInterceptor: Interceptor{

    private var context: Context? = null
    private var loginResponseFile : String? = null

    constructor(context: Context){
        this.context = context
        loginResponseFile = "responses/login.response"
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        var url = chain?.request()?.url()?.toString()
        var response :Response? = null
        var responseString: String? = null

        var originalUrl = url?.substring(url?.lastIndexOf("/").plus(1))
        if(originalUrl!=null && originalUrl.contains("?")){
            originalUrl = originalUrl.substring(originalUrl.indexOf("?"))
        }



        responseString = getResponseString("responses/"+originalUrl+".response")
        response = Response.Builder()
                .code(200)
                .message("Success")
                .protocol(Protocol.HTTP_1_1)
                .request(chain?.request())
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString))
                .addHeader("content-type", "application/json")
                .build();

        if(response.code() != 200){

        }

        Thread.sleep(Constants.MOCK_API_DELAY_TIME)

        return response!!
    }

    private fun getResponseString(filePath: String?):String {
        var reader : BufferedReader? = null
        var stringBuilder = StringBuilder()
        try {
            reader = BufferedReader(
                    InputStreamReader(context?.assets?.open(filePath), Charsets.UTF_8));

            // do reading, usually loop until end of file reading
            var line : String? = null
            while (true) {
                line = reader.readLine()
                if(line ==null)
                    break;
                stringBuilder.append(line)
            }
        } catch (err : IOException) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (err : IOException) {
                    //log the exception
                }
            }
        }
        return stringBuilder.toString()
    }
}
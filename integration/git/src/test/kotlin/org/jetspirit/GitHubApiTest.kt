package org.jetspirit

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class GitHubApiTest {
    lateinit var client: GitHubApi

    @BeforeAll
    fun setU() {
        client = GitHubApi.getApi()
    }


    @Test
    fun getToken() {
        client.authorize()
    }
}
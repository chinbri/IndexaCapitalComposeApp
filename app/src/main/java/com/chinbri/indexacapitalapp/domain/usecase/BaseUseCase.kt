package com.chinbri.indexacapitalapp.domain.usecase

fun interface BaseUseCase<Request, Response>{

    suspend operator fun invoke(request: Request): Response

}
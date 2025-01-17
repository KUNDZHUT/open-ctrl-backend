package com.kndzhut.open_control.usecase.users

import com.kndzhut.open_control.domain.Role
import com.kndzhut.open_control.infra.repository.info.InfoRepository
import com.kndzhut.open_control.usecase.utils.Error
import com.kndzhut.open_control.usecase.utils.Request
import com.kndzhut.open_control.usecase.utils.Response
import com.kndzhut.open_control.usecase.utils.UseCase
import com.kndzhut.open_control.usecase.utils.UseCaseResult
import org.springframework.stereotype.Component
import java.util.*


@Component
class LoginUserUseCase(
    val infoRepository: InfoRepository
) : UseCase<LoginUserRequest, LoginUserResponse, LoginUserError> {
    override fun execute(request: LoginUserRequest): UseCaseResult<LoginUserResponse, LoginUserError> =
        infoRepository.loginUser(request.login, request.password, request.role)
            .let { UseCaseResult.success(LoginUserResponse(it)) }
}

class LoginUserRequest(
    val login: String,
    val password: String,
    val role: Role
) : Request

class LoginUserResponse(
    val uid: UUID
) : Response

class LoginUserError(
    val code: Code
) : Error {
    enum class Code {
        NO_SUCH_USER
    }
}
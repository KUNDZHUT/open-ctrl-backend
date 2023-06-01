package com.kndzhut.open_control.usecase.users

import com.kndzhut.open_control.domain.Role
import com.kndzhut.open_control.infra.repository.info.InfoRepository
import com.kndzhut.open_control.usecase.utils.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateUserUseCase(
    val infoRepository: InfoRepository
) : UseCase<CreateUserRequest, CreateUserResponse, CreateUserError> {
    override fun execute(request: CreateUserRequest): UseCaseResult<CreateUserResponse, CreateUserError> =
        infoRepository.createUser(request.login, request.role, request.password).let { UseCaseResult.success(CreateUserResponse(it)) }
}

class CreateUserRequest(
    val login: String,
    val role: Role,
    val password: String
) : Request

class CreateUserResponse(
    val uid: UUID
) : Response

class CreateUserError(
    val code: Code
) : Error {
    enum class Code {
        USER_ALREADY_EXISTS
    }
}


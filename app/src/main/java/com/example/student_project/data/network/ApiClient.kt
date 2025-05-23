package com.example.student_project.data.network

import com.example.student_project.data.model.AiChatHistoryResponse
import com.example.student_project.data.model.Allin
import com.example.student_project.data.model.Category
import com.example.student_project.data.model.ChattingRoom
import com.example.student_project.data.model.Course
import com.example.student_project.data.model.InboxChat
import com.example.student_project.data.model.Instructor
import com.example.student_project.data.model.Meeting
import com.example.student_project.data.model.Message
import com.example.student_project.data.model.Student
import com.example.student_project.data.model.User
import com.example.student_project.data.network.request.ApiBodyForCaptureAndVerifyPayment
import com.example.student_project.data.network.request.ApiBodyForResetPassword
import com.example.student_project.data.network.request.ApiBodyForUpdateProgress
import com.example.student_project.data.network.request.ApiBodyReqForGettingAvgRating
import com.example.student_project.data.network.request.ApiReqForAllinAi
import com.example.student_project.data.network.request.ApiReqForChat
import com.example.student_project.data.network.request.ApiReqForChatBootAi
import com.example.student_project.data.network.request.ApiReqForMessageInChat
import com.example.student_project.data.network.request.ApiReqForSendingMessage
import com.example.student_project.data.network.request.CapturePayment
import com.example.student_project.data.network.request.CreateRatingReq
import com.example.student_project.data.network.request.ApiRequestWithCourseId
import com.example.student_project.data.network.request.ApiRequestWithInstructorId
import com.example.student_project.data.network.request.StudentLogin
import com.example.student_project.data.network.request.StudentUpdateRequest
import com.example.student_project.data.network.request.TokenReq
import com.example.student_project.data.network.request.VerifyPayment
import com.example.student_project.data.network.response.BaseResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiClient {

    @POST("auth/login")
    suspend fun login(@Body studentLogin: StudentLogin): BaseResponse<User>

    @POST("auth/signup")
    suspend fun addStudent(@Body student: Student)

    @GET("course/getAllCourses")
    suspend fun getAllCourses(): BaseResponse<List<Course>>

    @POST("course/getFullCourseDetails")
    suspend fun getFullCourseDetails(@Body courseId: ApiRequestWithCourseId): BaseResponse<Course>

    @PUT("profile/updateProfile")
    suspend fun updateProfile(@Body student: StudentUpdateRequest): BaseResponse<User>


    @POST("paymob/initiate-payment")
    suspend fun initiatePayment(@Body capturePayment: CapturePayment): BaseResponse<String>

    @POST("payment/capturePayment")
    suspend fun capturePayment(@Body apiBodyForCaptureAndVerifyPayment: ApiBodyForCaptureAndVerifyPayment): BaseResponse<String>


    @POST("paymob/getTransactionStatus")
    suspend fun getTransactionState(@Body courseId: VerifyPayment): BaseResponse<String>

    @POST("payment/verifyPayment")
    suspend fun verifyPayment(@Body apiBodyForCaptureAndVerifyPayment: ApiBodyForCaptureAndVerifyPayment): BaseResponse<Boolean>

    @POST("course/createRating")
    suspend fun createRating(@Body ratingReq: CreateRatingReq):BaseResponse<String>

    @POST("course/getAverageRating")
    suspend fun getAverageRating(@Body apiBodyReqForGettingAvgRating: ApiBodyReqForGettingAvgRating): BaseResponse<Number>

    @POST("profile/saveCourse")
    suspend fun saveCourse(@Body courseId: ApiRequestWithCourseId): BaseResponse<String>

    @POST("course/getInstructorDetails")
    suspend fun getInstructorDetails(@Body instructorId: ApiRequestWithInstructorId): BaseResponse<Instructor>

    @POST("course/createChat")
    suspend fun createChat(@Body participantId: ApiReqForChat): BaseResponse<ChattingRoom>

    @POST("course/sendMessage")
    suspend fun sendMessage(@Body content: ApiReqForSendingMessage): BaseResponse<Message>

    @GET("course/getAllChats")
    suspend fun getAllChat(): BaseResponse<List<InboxChat>>

    @POST("course/getMessages")
    suspend fun getMessages(@Body chatId: ApiReqForMessageInChat): BaseResponse<List<Message>>

    @GET("meeting/all")
    suspend fun getAllMeeting(): BaseResponse<List<Meeting>>

    @GET("profile/getSavedCourses")
    suspend fun getSavedCourses(): BaseResponse<List<Course>>

    @GET("profile/getEnrolledCourses")
    suspend fun getEnrolledCourses(): BaseResponse<List<Course>>

    @GET("course/getAllCoursesProgress")
    suspend fun getAllCourseProgress(): BaseResponse<List<Course>>

    @GET("auth/all-instructors")
    suspend fun allInstructors(): BaseResponse<List<Instructor>>

    @GET("course/showAllCategories")
    suspend fun showAllCategories(): BaseResponse<List<Category>>

    @POST("auth/reset-password-token")
    suspend fun resetPasswordToken(@Body email: TokenReq): BaseResponse<String>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body apiBodyForResetPassword: ApiBodyForResetPassword): BaseResponse<String>

    @POST("course/updateCourseProgress")
    suspend fun updateCourseProgress(@Body apiBodyForUpdateProgress: ApiBodyForUpdateProgress): BaseResponse<String>

    @Multipart
    @PUT("profile/updateUserProfileImage")
    suspend fun updateUserProfileImage(@Part profileImage: MultipartBody.Part): BaseResponse<User>

    //start of ai end point
    @POST("projects/")
    suspend fun createProject(@Body apiReqForAllinAi: ApiReqForAllinAi): BaseResponse<Allin>

    @GET("projects/")
    suspend fun getProject(): BaseResponse<List<Allin>>

    @POST("openAi/chat")
    suspend fun createAiChat(@Body apiReqForChatBootAi: ApiReqForChatBootAi): BaseResponse<String>

    @GET("openAi/chat")
    suspend fun getAiChats(): BaseResponse<List<AiChatHistoryResponse>>
}

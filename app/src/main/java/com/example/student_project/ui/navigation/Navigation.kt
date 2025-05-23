package com.example.student_project.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.student_project.DepContainer
import com.example.student_project.ui.screen.inbox.InboxScreen
import com.example.student_project.ui.screen.mycourses.MyCoursesScreen
import com.example.student_project.ui.screen.details.course.CourseDetailsScreen
import com.example.student_project.ui.screen.details.course.CourseLessonScreen
import com.example.student_project.ui.screen.home.content.HomeScreen
import com.example.student_project.ui.screen.home.filtering.filteration.CourseFilterScreen
import com.example.student_project.ui.screen.home.filtering.filteration.MentorFilterScreen
import com.example.student_project.ui.screen.home.filtering.filterationresult.CourseFilterResultScreen
import com.example.student_project.ui.screen.home.filtering.filterationresult.MentorFilterResultScreen
import com.example.student_project.ui.screen.SplashScreen
import com.example.student_project.ui.screen.ai.ChatBootHistoryScreen
import com.example.student_project.ui.screen.ai.ChatBootScreen
import com.example.student_project.ui.screen.ai.GitHubAiHistoryScreen
import com.example.student_project.ui.screen.ai.GitHubAiScreen
import com.example.student_project.ui.screen.details.mentor.MentorDetailsScreen
import com.example.student_project.ui.screen.home.allcourses.AllCourseScreen
import com.example.student_project.ui.screen.home.allmentor.AllMentorScreen
import com.example.student_project.ui.screen.home.trendingcourses.TrendingCourseScreen
import com.example.student_project.ui.screen.inbox.chat.InboxChatScreen
import com.example.student_project.ui.screen.log.forgetpassword.EmailAndPhoneScreen
import com.example.student_project.ui.screen.log.forgetpassword.NewPasswordScreen
import com.example.student_project.ui.screen.log.forgetpassword.OtpTokenScreen
import com.example.student_project.ui.screen.log.login.LoginScreen
import com.example.student_project.ui.screen.log.signup.SignupScreen
import com.example.student_project.ui.screen.profile.ProfileScreen
import com.example.student_project.ui.screen.profile.editprofile.EditProfileScreen
import com.example.student_project.ui.screen.profile.helpcenter.HelpCenterScreen
import com.example.student_project.ui.screen.profile.invitefriends.InviteFriendsScreen
import com.example.student_project.ui.screen.profile.notification.NotificationScreen
import com.example.student_project.ui.screen.profile.payment.PaymentScreen
import com.example.student_project.ui.screen.profile.privacypolicy.PrivacyPolicyScreen
import com.example.student_project.ui.screen.profile.security.SecurityScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(depContainer: DepContainer) {
    val navController = rememberNavController()
    // start from splash
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(
                navController,
                depContainer.studentRepo
            )
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController, depContainer.studentRepo)
        }
        composable(
            Screens.SignupScreen.route
        ) { backStackEntry ->
            SignupScreen(
                navController = navController,
                depContainer.studentRepo
            )
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController,
                depContainer.courseRepo,
                depContainer.studentRepo,
                depContainer.instructorRepo
            )
        }
        composable(Screens.AllCourseScreen.route) {
            AllCourseScreen(navController = navController, depContainer.courseRepo)
        }
        composable(Screens.TrendingCourseScreen.route) {
            TrendingCourseScreen(navController = navController, depContainer.courseRepo)
        }
        composable(
            Screens.CourseDetailScreen.route + "/{course_title}",
            arguments = (listOf(navArgument(name = "course_title") { type = NavType.StringType })),
        ) { backStackEntry ->
            CourseDetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getString("course_title"),
                depContainer.courseRepo,
            )
        }
        composable(Screens.LearningScreen.route) {
            MyCoursesScreen(
                navController = navController,
                depContainer.courseRepo
            )
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(
                navController = navController,
                depContainer.studentRepo
            )
        }
        composable(Screens.MentorFilterScreen.route) {
            // filled parcel object "FiltrationResult" and send it to search result screen
            MentorFilterScreen(navController)
        }
        composable(
            Screens.MentorDetailsScreen.route + "/{instructor_id}", arguments = listOf(
                navArgument(name = "instructor_id") { type = NavType.StringType })
        ) { backStackEntry ->
            MentorDetailsScreen(
                navController = navController,
                instructorId = backStackEntry.arguments?.getString("instructor_id"),
                depContainer.instructorRepo,
                depContainer.studentRepo
            )

        }
        composable(
            Screens.MentorFilterResultScreen.route +
                    "/{jop_title}" +
                    "/{rating}" +
                    "/{hourly_rate}",
            arguments =
            (listOf(
                navArgument(name = "jop_title") { type = NavType.StringType },
                navArgument(name = "rating") { type = NavType.FloatType },
                navArgument(name = "hourly_rate") { type = NavType.FloatType },
            )),
        ) { backStackEntry ->
            MentorFilterResultScreen(
                navController = navController,
                depContainer.studentRepo,
                backStackEntry.arguments?.getString("jop_title"),
                backStackEntry.arguments?.getFloat("rating"),
                backStackEntry.arguments?.getFloat("hourly_rate"),
                depContainer.instructorRepo
            )
        }
        // when we click
        // we will send this data to detailsScreen
        // here will be course details screen

        composable(Screens.CourseFilterScreen.route) {
            // filled parcel object "FiltrationResult" and send it to search result screen
            CourseFilterScreen(navController)
        }
        //
        composable(
            Screens.CourseFilterResultScreen.route +
                    "/{course_category}" +
                    "/{difficulty_level}" +
                    "/{released_date}" +
                    "/{rating}" +
                    "/{hourly_rate}",
            arguments =
            (listOf(
                navArgument(name = "course_category") { type = NavType.StringType },
                navArgument(name = "difficulty_level") { type = NavType.StringType },
                navArgument(name = "released_date") { type = NavType.StringType },
                navArgument(name = "rating") { type = NavType.FloatType },
                navArgument(name = "hourly_rate") { type = NavType.FloatType },
            )),
        ) { backStackEntry ->
            CourseFilterResultScreen(
                navController,
                backStackEntry.arguments?.getString("course_category"),
                backStackEntry.arguments?.getString("difficulty_level"),
                backStackEntry.arguments?.getString("released_date"),
                backStackEntry.arguments?.getFloat("rating"),
                backStackEntry.arguments?.getFloat("hourly_rate"),
                depContainer.courseRepo,
            )
        }
        // here we will send this info to course result screen
        // and then we will send this info to course details screen
        // there will be 2 fun one for courseResultScreen
        // and other one will be for courseDetailsScreen
        // will activate when we click on a course

        composable(
            Screens.EmailAndPhoneScreen.route + "/{user_email}",
            arguments = listOf(navArgument(name = "user_email") { type = NavType.StringType }),
        ) { backStackEntry ->
            EmailAndPhoneScreen(
                navController = navController,
                backStackEntry.arguments?.getString("user_email"),
            )
        }
        composable(Screens.NewPasswordScreen.route + "/{otp_token}" + "/{email}",
            arguments = listOf(
                navArgument(name = "otp_token") { type = NavType.StringType },
                navArgument(name = "email") { type = NavType.StringType }
            )) { backStackEntry ->
            NewPasswordScreen(
                navController = navController,
                backStackEntry.arguments?.getString("otp_token"),
                backStackEntry.arguments?.getString("email"),
                depContainer.studentRepo
            )
        }
        composable(
            Screens.OtpTokenScreen.route + "/{user_email}",
            arguments = listOf(navArgument(name = "user_email") { type = NavType.StringType }),
        ) { backStackEntry ->
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            OtpTokenScreen(
                navController = navController,
                backStackEntry.arguments?.getString("user_email"),
                depContainer.studentRepo
            )
        }
        composable(Screens.EditProfileScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            EditProfileScreen(navController = navController, depContainer.studentRepo)
        }
        composable(Screens.NotificationScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            NotificationScreen(navController = navController)
        }
        composable(Screens.PaymentScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            PaymentScreen(navController = navController)
        }
        composable(Screens.SecurityScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            SecurityScreen(navController = navController)
        }
        composable(Screens.PrivacyPolicyScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            PrivacyPolicyScreen(navController = navController)
        }
        composable(Screens.HelpCenterScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            HelpCenterScreen(navController = navController)
        }
        composable(Screens.InviteFriendsScreen.route) {
            // we should take a phone or email from previous screen
            // then send it to backend and get otp code
            // then pass it if true move to next screen
            InviteFriendsScreen(navController = navController)
        }
        ////////////////////////////
        composable(Screens.GitHubAiScreen.route) {

            GitHubAiScreen(navController = navController, depContainer.studentRepo)
        }
        composable(Screens.GitHubAiHistoryScreen.route) {

            GitHubAiHistoryScreen(navController = navController, depContainer.studentRepo)
        }
        composable(Screens.ChatBootScreen.route) {

            ChatBootScreen(navController = navController, depContainer.studentRepo)
        }
        composable(Screens.ChatBootHistoryScreen.route) {

            ChatBootHistoryScreen(navController = navController, depContainer.studentRepo)
        }
        /////////////////

        composable(Screens.InboxScreen.route) {
            InboxScreen(navController = navController, depContainer.studentRepo)
        }
        composable(Screens.InboxChatScreen.route + "/{chat_id}" + "/{chat_name}",
            arguments = listOf(
                navArgument(name = "chat_id") { type = NavType.StringType },
                navArgument(name = "chat_name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            InboxChatScreen(
                navController = navController,
                backStackEntry.arguments?.getString("chat_id"),
                backStackEntry.arguments?.getString("chat_name"),
                depContainer.studentRepo
            )
        }
        composable(
            Screens.CourseLessonScreen.route + "/{video_url}" + "/{course_id}" + "/{lesson_id}",
            arguments = listOf(navArgument(name = "video_url") { type = NavType.StringType },
                navArgument(name = "course_id") { type = NavType.StringType },
                navArgument(name = "lesson_id") { type = NavType.StringType }
            ),
        ) { backStackEntry ->
            CourseLessonScreen(
                navController = navController,
                backStackEntry.arguments?.getString("video_url"),
                depContainer.courseRepo,
                backStackEntry.arguments?.getString("course_id"),
                backStackEntry.arguments?.getString("lesson_id")
            )
        }
        composable(
            Screens.AllMentorScreen.route
        ) {
            AllMentorScreen(
                navController = navController,
                studentRepo = depContainer.studentRepo,
                depContainer.instructorRepo
            )
        }
    }
}

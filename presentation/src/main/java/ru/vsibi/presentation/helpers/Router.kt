package ru.vsibi.presentation.helpers

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import ru.vsibi.domain.network.response.ResponseHotel
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.presentation.models.PersonalDataModel
import ru.vsibi.presentation.models.SearchModel
import ru.vsibi.presentation.models.flight.FlightResponse
import ru.vsibi.presentation.screens.flights.FlightsFragmentDirections
import ru.vsibi.presentation.screens.flights.exclude.ExcludeCountriesFragmentDirections
import ru.vsibi.presentation.screens.tours.main.TourModel
import ru.vsibi.presentation.screens.login.LoginFragmentDirections
import ru.vsibi.presentation.screens.login.emailVariant.createPassword.CreatePassFragmentDirections
import ru.vsibi.presentation.screens.login.emailVariant.email.LoginEmailFragmentDirections
import ru.vsibi.presentation.screens.login.emailVariant.forgotPassword.ForgotPassFragmentDirections
import ru.vsibi.presentation.screens.login.emailVariant.password.LoginPasswordFragmentDirections
import ru.vsibi.presentation.screens.main.MainFragment
import ru.vsibi.presentation.screens.main.MainFragmentDirections
import ru.vsibi.presentation.screens.profile.coTravellers.CoTravallersFragmentDirections
import ru.vsibi.presentation.screens.profile.main.ProfileFragmentDirections
import ru.vsibi.presentation.screens.profile.orders.info.OrdersDetailFragmentDirections
import ru.vsibi.presentation.screens.profile.orders.main.OrdersFragmentDirections
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataFragment
import ru.vsibi.presentation.screens.saved.SavedFragmentDirections
import ru.vsibi.presentation.screens.search.main.SearchFragmentDirections
import ru.vsibi.presentation.screens.tours.info.ToursInfoFragmentDirections
import ru.vsibi.presentation.screens.tours.info.flights.main.FlightsListFragmentDirections
import ru.vsibi.presentation.screens.tours.main.ToursFragmentDirections
import ru.vsibi.presentation.screens.tours.purchase.PurchaseFormFragmentDirections
import ru.vsibi.presentation.screens.tours.purchase.paymentVariants.PayVariantsFragmentDirections

class Router {

    companion object {
        val instance = Router()
    }

    var mainFragmentInstance: MainFragment? = null
    var parentNavController: NavController? = null
    var parentNavHostFragment: NavHostFragment? = null
    var childNavHostFragment: NavHostFragment? = null
    var childNavController: NavController? = null

    fun navigateToTravellers() {
        childNavController?.navigate(SearchFragmentDirections.actionSearchFragmentToTravellersFragment())
    }

    fun navigateToLogin() {
        parentNavController?.navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
    }

    fun navigateToHotelsInfo(hotelsModel: ResponseSearch.Result) {
        childNavController?.navigate(ToursFragmentDirections.actionHotelsFragmentToHotelsInfoFragment(hotelsModel))
    }

    fun navigateHotels(searchModel: ResponseSearch) {
        childNavController?.navigate(SearchFragmentDirections.actionSearchFragmentToHotelsFragment(searchModel))
    }

    fun navigatePurchaseForm(hotelsModel: TourModel) {
        childNavController?.navigate(ToursInfoFragmentDirections.actionHotelsInfoFragmentToPurchaseFormFragment(hotelsModel))
    }

    fun navigateToLoginWithEmail(isLogin: Boolean) {
        val directions = LoginFragmentDirections.actionLoginFragmentToLoginEmailFragment()
        directions.isLogin = isLogin
        parentNavController?.navigate(directions)
    }

    fun navigateToLoginPassword(data: String) {
        parentNavController?.navigate(LoginEmailFragmentDirections.actionLoginEmailFragmentToLoginPasswordFragment(data))
    }

    fun navigateToMainFromEmailLogin() {
        parentNavController?.navigate(LoginPasswordFragmentDirections.actionLoginPasswordFragmentToMainFragment())
    }

    fun reopenApp() {
        parentNavController?.navigate(MainFragmentDirections.actionMainFragmentSelf())
    }

    fun navigateToCreatePassFromForgot(email : String) {
        parentNavController?.navigate(ForgotPassFragmentDirections.actionForgotPassFragmentToCreatePassFragment(email))
    }

    fun navigateForgotPass(email: String) {
        parentNavController?.navigate(
            LoginPasswordFragmentDirections.actionLoginPasswordFragmentToForgotPassFragment(
                email
            )
        )
    }

    fun navigateToMainFromCreatePass() {
        parentNavController?.navigate(CreatePassFragmentDirections.actionCreatePassFragmentToMainFragment())
    }

    fun navigateToMyOrders() {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToOrdersFragment())
    }

    fun navigateToOrdersDetail(hotel: ResponseSearch.Result) {
        childNavController?.navigate(OrdersFragmentDirections.actionOrdersFragmentToOrdersDetailFragment())
    }

    fun navigateToOrdersDetailFromSaved(hotel: ResponseSearch.Result) {
        childNavController?.navigate(SavedFragmentDirections.actionSavedFragmentToHotelsInfoFragment(hotel))
    }

    fun navigateToTicket() {
        childNavController?.navigate(OrdersDetailFragmentDirections.actionOrdersDetailFragmentToTicketFragment())
    }

    fun navigateTourMore(tour: ResponseSearch.Result) {
        childNavController?.navigate(ToursInfoFragmentDirections.actionHotelsInfoFragmentToTourMoreFragment(tour))
    }

    fun navigateToFlightDetails() {
        childNavController?.navigate(ToursInfoFragmentDirections.actionTourInfoFragmentToFlightsListFragment())
    }

    fun navigateToFlightInfo(fligths: FlightResponse) {
        childNavController?.navigate(FlightsListFragmentDirections.actionFlightsListFragmentToFlightsInfoFragment(fligths))
    }

    fun navigateToPersonalData(person: PersonalDataModel?, mode : PersonalDataFragment.Mode) {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToPersonalDataFragment(person, mode))
    }

    fun navigateToCoTravellers() {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToCoTravallersFragment())
    }

    fun navigateToPersonalDataFromCoTravellers(person: PersonalDataModel?) {
        childNavController?.navigate(CoTravallersFragmentDirections.actionCoTravallersFragmentToPersonalDataFragment(person, PersonalDataFragment.Mode.PERSON))
    }

    fun navigateToChangePass() {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment())
    }

    fun navigateToCreatePassFromSignup(data: String) {
        parentNavController?.navigate(LoginEmailFragmentDirections.actionLoginEmailFragmentToCreatePassFragment(data))
    }

    fun navigateToPersonalDataFromPurchaseForm(person: PersonalDataModel) {
        childNavController?.navigate(PurchaseFormFragmentDirections.actionPurchaseFormFragmentToPersonalDataFragment(person, PersonalDataFragment.Mode.PERSON))
    }

    fun navigateToursSettings() {
        childNavController?.navigate(ToursFragmentDirections.actionHotelsFragmentToToursSettingsFragment())
    }

    fun navigateToExcludeCountries(){
        childNavController?.navigate(FlightsFragmentDirections.actionFligthsFragmentToExcludeCountriesFragment())
    }
    fun navigateToAddCards() {
        childNavController?.navigate(PurchaseFormFragmentDirections.actionPurchaseFormFragmentToPayVariantsFragment())
    }

    fun navigateToAddCard() {
        childNavController?.navigate(PayVariantsFragmentDirections.actionPayVariantsFragmentToAddCardFragment())
    }

    fun navigateToAddCountries() {
        childNavController?.navigate(ExcludeCountriesFragmentDirections.actionExcludeCountriesFragmentToAddCountriesFragment())
    }

    fun navigateToMainFromLogin() {
        parentNavController?.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

    fun openDateRangeDialog() {
        childNavController?.navigate(SearchFragmentDirections.actionSearchFragmentToDateRangeDialog())
    }

    fun navigateToPhotoActionDialog() {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToProfilePhotoAction())
    }

    fun navigatePhotoViewerFromTourInfo(listOf: List<String>) {
        childNavController?.navigate(ToursInfoFragmentDirections.actionTourInfoFragmentToPhotoViewerFragment(listOf.toTypedArray()))
    }

    fun navigatePhotoViewerFromProfile(listOf: List<String>) {
        childNavController?.navigate(ProfileFragmentDirections.actionProfileFragmentToPhotoViewerFragment(listOf.toTypedArray()))
    }
}
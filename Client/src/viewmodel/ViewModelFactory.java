package viewmodel;

import model.Model;
import view.PublishOfferViewController;

/**
 * The class is used to store, to construct and to obtain always the same objects of the ViewModel that the system needs.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */

public class ViewModelFactory {
    private final LoginViewModel loginViewModel;
    private final SignUpViewModel signUpViewModel;
    private final HomePageViewModel homePageViewModel;
    private final UserViewViewModel userViewViewModel;
    private final OffersListViewModel offersListViewModel;
    private final PublishOfferViewModel publishOfferViewModel;
    private final OfferViewViewModel offerViewViewModel;
    private final SendMessageViewModel sendMessageViewModel;
    private final RentingViewViewModel rentingViewViewModel;
    private final RentingListViewModel rentingListViewModel;
    private final MessageViewViewModel messageViewViewModel;

    /**
     * One-argument constructor which initializes all the ViewModel objects.
     * @param model The model to which all the viewModel files will delegate some methods.
     */
    public ViewModelFactory(Model model){
        this.loginViewModel=new LoginViewModel(model);
        this.signUpViewModel=new SignUpViewModel(model);
        this.homePageViewModel=new HomePageViewModel(model);
        this.userViewViewModel=new UserViewViewModel(model);
        this.offersListViewModel=new OffersListViewModel(model);
        this.publishOfferViewModel=new PublishOfferViewModel(model);
        this.offerViewViewModel=new OfferViewViewModel(model);
        this.sendMessageViewModel=new SendMessageViewModel(model);
        this.rentingListViewModel=new RentingListViewModel(model);
        this.rentingViewViewModel=new RentingViewViewModel(model);
        this.messageViewViewModel=new MessageViewViewModel(model);
    }

    /**
     * Getter for the Login ViewModel.
     * @return LoginViewModel object of the ViewModel.
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    /**
     * Getter for the SignUp ViewModel.
     * @return SignUpViewModel object of the ViewModel.
     */
    public SignUpViewModel getSignUpViewModel() {
        return signUpViewModel;
    }

    /**
     * Getter for the HomePage ViewModel.
     * @return HomePageViewModel object of the ViewModel.
     */
    public HomePageViewModel getHomePageViewModel() {
        return homePageViewModel;
    }

    /**
     * Getter for the UserView ViewModel.
     * @return UserViewViewModel object of the ViewModel.
     */
    public UserViewViewModel getUserViewViewModel() {
        return userViewViewModel;
    }

    /**
     * Getter for the OffersList ViewModel.
     * @return OffersListViewModel object of the ViewModel.
     */
    public OffersListViewModel getOffersListViewModel() {
        return offersListViewModel;
    }

    /**
     * Getter for the PublishOffer ViewModel.
     * @return PublishOfferViewModel object of the ViewModel.
     */
    public PublishOfferViewModel getPublishOfferViewModel() {
        return publishOfferViewModel;
    }

    /**
     * Getter for the OfferView ViewModel.
     * @return OfferViewViewModel object of the ViewModel.
     */
    public OfferViewViewModel getOfferViewViewModel() {
        return offerViewViewModel;
    }

    /**
     * Getter for the SendMessage ViewModel.
     * @return SendMessageViewModel object of the ViewModel.
     */
    public SendMessageViewModel getSendMessageViewModel() {
        return sendMessageViewModel;
    }

    /**
     * Getter for the RentingList ViewModel.
     * @return RentingListViewModel object of the ViewModel.
     */
    public RentingListViewModel getRentingListViewModel() {
        return rentingListViewModel;
    }

    /**
     * Getter for the RentingView ViewModel.
     * @return RentingViewViewModel object of the ViewModel.
     */
    public RentingViewViewModel getRentingViewViewModel() {
        return rentingViewViewModel;
    }

    /**
     * Getter for the MessageView ViewModel.
     * @return MessageViewViewModel object of the ViewModel.
     */
    public MessageViewViewModel getMessageViewViewModel() {
        return messageViewViewModel;
    }
}

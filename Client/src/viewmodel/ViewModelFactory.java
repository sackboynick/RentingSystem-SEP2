package viewmodel;

import model.Model;
import view.PublishOfferViewController;

public class ViewModelFactory {
    private final LoginViewModel loginViewModel;
    private final SignUpViewModel signUpViewModel;
    private final HomePageViewModel homePageViewModel;
    private final UserViewViewModel userViewViewModel;
    private final OffersListViewModel offersListViewModel;
    private final PublishOfferViewModel publishOfferViewModel;
    private final OfferViewViewModel offerViewViewModel;
    private final SendMessageViewModel sendMessageViewModel;

    public ViewModelFactory(Model model){
        this.loginViewModel=new LoginViewModel(model);
        this.signUpViewModel=new SignUpViewModel(model);
        this.homePageViewModel=new HomePageViewModel(model);
        this.userViewViewModel=new UserViewViewModel();
        this.offersListViewModel=new OffersListViewModel(model);
        this.publishOfferViewModel=new PublishOfferViewModel(model);
        this.offerViewViewModel=new OfferViewViewModel(model);
        this.sendMessageViewModel=new SendMessageViewModel(model);
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public SignUpViewModel getSignUpViewModel() {
        return signUpViewModel;
    }

    public HomePageViewModel getHomePageViewModel() {
        return homePageViewModel;
    }

    public UserViewViewModel getUserViewViewModel() {
        return userViewViewModel;
    }

    public OffersListViewModel getOffersListViewModel() {
        return offersListViewModel;
    }

    public PublishOfferViewModel getPublishOfferViewModel() {
        return publishOfferViewModel;
    }

    public OfferViewViewModel getOfferViewViewModel() {
        return offerViewViewModel;
    }

    public SendMessageViewModel getSendMessageViewModel() {
        return sendMessageViewModel;
    }
}

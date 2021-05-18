package viewmodel;

import com.sun.tools.javac.Main;
import model.Model;

public class ViewModelFactory {
    private final MainViewViewModel mainViewViewModel;
    private final UserViewViewModel userViewViewModel;
    private final OfferViewViewModel offerViewViewModel;
    private final UsersListViewModel usersListViewModel;

    public ViewModelFactory(Model model){
        this.mainViewViewModel=new MainViewViewModel(model);
        this.userViewViewModel=new UserViewViewModel();
        this.offerViewViewModel=new OfferViewViewModel(model);
        this.usersListViewModel=new UsersListViewModel(model);
    }

    public MainViewViewModel getMainViewViewModel() {
        return mainViewViewModel;
    }

    public UserViewViewModel getUserViewViewModel() {
        return userViewViewModel;
    }

    public OfferViewViewModel getOfferViewViewModel() {
        return offerViewViewModel;
    }

    public UsersListViewModel getUsersListViewModel() {
        return usersListViewModel;
    }
}

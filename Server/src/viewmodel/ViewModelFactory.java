package viewmodel;

import model.Model;

/**
 * The class is used to store, to construct and to obtain always the same objects of the ViewModel that the system needs.
 * @author Group8-SEP2
 * @version 1.0.0 2021
 */
public class ViewModelFactory {
    private final MainViewViewModel mainViewViewModel;
    private final UserViewViewModel userViewViewModel;
    private final OfferViewViewModel offerViewViewModel;
    private final UsersListViewModel usersListViewModel;
    private final OffersListViewModel offersListViewModel;
    private final ServerOverviewViewModel serverOverviewViewModel;
    private final RentingListViewModel rentingListViewModel;

    /**
     * One-argument constructor which initializes all the ViewModel objects.
     * @param model The model to which all the viewModel files will delegate some methods.
     */
    public ViewModelFactory(Model model){
        this.mainViewViewModel=new MainViewViewModel(model);
        this.userViewViewModel=new UserViewViewModel(model);
        this.offerViewViewModel=new OfferViewViewModel(model);
        this.usersListViewModel=new UsersListViewModel(model);
        this.offersListViewModel=new OffersListViewModel(model);
        this.serverOverviewViewModel=new ServerOverviewViewModel(model);
        this.rentingListViewModel=new RentingListViewModel(model);
    }

    /**
     * Getter for the MainView ViewModel.
     * @return MainViewViewModel object of the ViewModel.
     */
    public MainViewViewModel getMainViewViewModel() {
        return mainViewViewModel;
    }

    /**
     * Getter for the UserView ViewModel.
     * @return UserViewViewModel object of the ViewModel.
     */
    public UserViewViewModel getUserViewViewModel() {
        return userViewViewModel;
    }

    /**
     * Getter for the OfferView ViewModel.
     * @return OfferViewViewModel object of the ViewModel.
     */
    public OfferViewViewModel getOfferViewViewModel() {
        return offerViewViewModel;
    }

    /**
     * Getter for the UsersList ViewModel.
     * @return UsersListViewModel object of the ViewModel.
     */
    public UsersListViewModel getUsersListViewModel() {
        return usersListViewModel;
    }

    /**
     * Getter for the OffersList ViewModel.
     * @return OffersListViewModel object of the ViewModel.
     */
    public OffersListViewModel getOffersListViewModel() {
        return offersListViewModel;
    }

    /**
     * Getter for the ServerOverview ViewModel.
     * @return ServerOverviewViewModel object of the ViewModel.
     */
    public ServerOverviewViewModel getServerOverviewViewModel() {
        return serverOverviewViewModel;
    }

    /**
     * Getter for the RentingList ViewModel.
     * @return RentingListViewModel object of the ViewModel.
     */
    public RentingListViewModel getRentingListViewModel() {
        return rentingListViewModel;
    }
}

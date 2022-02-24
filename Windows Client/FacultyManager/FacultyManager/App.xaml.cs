using FacultyManager.Service;
using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Windows;

namespace FacultyManager
{
    public partial class App : Application
    {
        private readonly AccountStore _accountStore;
        private readonly NavigationStore _navigationStore;
        private readonly ModalNavigationStore _modalNavigationStore;

        public App()
        {
            _accountStore = new AccountStore();
            _navigationStore = new NavigationStore();
            _modalNavigationStore = new ModalNavigationStore();
        }
        
        protected override void OnStartup(StartupEventArgs e)
        {
            INavigationService homeNavigationService = CreateHomeNavigationService();
            homeNavigationService.Navigate();

            MainWindow = new MainWindow()
            {
                DataContext = new MainViewModel(_navigationStore, _modalNavigationStore)
            };
            MainWindow.Show();

            base.OnStartup(e);
        }

        private INavigationService CreateHomeNavigationService()
        {
            return new LayoutNavigationService<HomeViewModel>(
                _navigationStore,
                () => new HomeViewModel(_accountStore),
                CreateNavigationBarViewModel);
        }

        private INavigationService CreateLoginNavigationService()
        {
            CompositeNavigationService navigationService = new CompositeNavigationService(
                new CloseModalNavigationService(_modalNavigationStore),
                CreateAccountNavigationService());

            return new ModalNavigationService<LoginViewModel>(
                _modalNavigationStore,
                () => new LoginViewModel(_accountStore, navigationService, new CloseModalNavigationService(_modalNavigationStore)));
        }

        private INavigationService CreateAccountNavigationService()
        {
            return new LayoutNavigationService<AccountViewModel>(
                _navigationStore,
                () => new AccountViewModel(_accountStore),
                CreateNavigationBarViewModel);
        }
        private INavigationService CreateRegisterNavigationService()
        {
            return new LayoutNavigationService<RegisterViewModel>(
                _navigationStore,
                () => new RegisterViewModel(CreateLoginNavigationService()),
                CreateNavigationBarViewModel);
        }
        private NavigationBarViewModel CreateNavigationBarViewModel()
        {
            return new NavigationBarViewModel(
                _accountStore,
                CreateHomeNavigationService(),
                CreateAccountNavigationService(),
                CreateLoginNavigationService(),
                CreateRegisterNavigationService());
        }
    }
}
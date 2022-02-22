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

        public App()
        {
            _accountStore = new AccountStore();
            _navigationStore = new NavigationStore();
        }
        
        protected override void OnStartup(StartupEventArgs e)
        {
            INavigationService<LoginViewModel> loginNavigationService = CreateLoginNavigationService();
            loginNavigationService.Navigate();

            MainWindow = new MainWindow()
            {
                DataContext = new MainViewModel(_navigationStore)
            };
            MainWindow.Show();

            base.OnStartup(e);
        }

        private INavigationService<HomeViewModel> CreateHomeNavigationService()
        {
            return new LayoutNavigationService<HomeViewModel>(
                _navigationStore,
                () => new HomeViewModel(_accountStore),
                CreateNavigationBarViewModel);
        }

        private INavigationService<LoginViewModel> CreateLoginNavigationService()
        {
            return new LayoutNavigationService<LoginViewModel>(
                _navigationStore,
                () => new LoginViewModel(_accountStore, CreateHomeNavigationService()),
                CreateNavigationBarViewModel);
        }

        private INavigationService<AccountViewModel> CreateAccountNavigationService()
        {
            return new LayoutNavigationService<AccountViewModel>(
                _navigationStore,
                () => new AccountViewModel(_accountStore),
                CreateNavigationBarViewModel);
        }
        private INavigationService<RegisterViewModel> CreateRegisterNavigationService()
        {
            return new LayoutNavigationService<RegisterViewModel>(
                _navigationStore,
                () => new RegisterViewModel(CreateLoginNavigationService()),
                CreateNavigationBarViewModel);
        }
        public NavigationBarViewModel CreateNavigationBarViewModel()
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
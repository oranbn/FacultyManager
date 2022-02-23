using FacultyManager.Commands;
using FacultyManager.Service;
using FacultyManager.Stores;
using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace FacultyManager.ViewModel
{
    public class NavigationBarViewModel : NotifiableObject
    {
        private readonly AccountStore _accountStore;

        public ICommand NavigateHomeCommand { get; }
        public ICommand NavigateAccountCommand { get; }
        public ICommand NavigateLoginCommand { get; }
        
        public ICommand NavigateRegisterCommand { get; }
        public ICommand LogoutCommand { get; }
        public bool IsLoggedIn => _accountStore.IsLoggedIn;
        public bool IsNotLoggedIn => _accountStore.IsNotLoggedIn;
        

        public NavigationBarViewModel(AccountStore accountStore,
            INavigationService<HomeViewModel> homeNavigationService, 
            INavigationService<AccountViewModel> accountNavigationService, 
            INavigationService<LoginViewModel> loginNavigationService,
            INavigationService<RegisterViewModel> registerNavigationService)
        {
            _accountStore = accountStore;
            NavigateHomeCommand = new NavigateCommand<HomeViewModel>(homeNavigationService);
            NavigateAccountCommand = new NavigateCommand<AccountViewModel>(accountNavigationService);
            NavigateLoginCommand = new NavigateCommand<LoginViewModel>(loginNavigationService);
            NavigateRegisterCommand = new NavigateCommand<RegisterViewModel>(registerNavigationService);
            LogoutCommand = new LogoutCommand(_accountStore, loginNavigationService);
            _accountStore.CurrentAccountChanged += OnCurrentAccountChanged;
        }

        private void OnCurrentAccountChanged()
        {
            RaisePropertyChanged(nameof(IsLoggedIn));
            RaisePropertyChanged(nameof(IsNotLoggedIn));
        }

        public override void Dispose()
        {
            _accountStore.CurrentAccountChanged -= OnCurrentAccountChanged;

            base.Dispose();
        }
    }
}
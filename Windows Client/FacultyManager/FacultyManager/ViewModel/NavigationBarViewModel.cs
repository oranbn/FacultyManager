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
            INavigationService homeNavigationService, 
            INavigationService accountNavigationService, 
            INavigationService loginNavigationService,
            INavigationService registerNavigationService)
        {
            _accountStore = accountStore;
            NavigateHomeCommand = new NavigateCommand(homeNavigationService);
            NavigateAccountCommand = new NavigateCommand(accountNavigationService);
            NavigateLoginCommand = new NavigateCommand(loginNavigationService);
            NavigateRegisterCommand = new NavigateCommand(registerNavigationService);
            LogoutCommand = new LogoutCommand(_accountStore, homeNavigationService);
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
using FacultyManager.Stores;
using System;
using System.Collections.Generic;
using System.Text;
using FacultyManager.Service;
using FacultyManager.ViewModel;

namespace FacultyManager.Commands
{
    public class LogoutCommand : CommandBase
    {
        private readonly AccountStore _accountStore;
        private readonly INavigationService _navigationService;

        public LogoutCommand(AccountStore accountStore,  INavigationService navigationService)
        {
            _accountStore = accountStore;
            _navigationService = navigationService;
        }

        public override void Execute(object parameter)
        {
            _accountStore.Logout();
            _navigationService.Navigate();
        }
    }
}
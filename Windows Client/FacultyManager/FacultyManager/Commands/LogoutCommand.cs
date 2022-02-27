using FacultyManager.Stores;
using System;
using System.Collections.Generic;
using System.Text;
using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.ViewModel;

namespace FacultyManager.Commands
{
    public class LogoutCommand : CommandBase
    {
        private readonly AccountStore _accountStore;
        private readonly INavigationService _navigationService;
        private readonly FacultyController _facultyController;

        public LogoutCommand(AccountStore accountStore,  INavigationService navigationService, FacultyController facultyController)
        {
            _accountStore = accountStore;
            _navigationService = navigationService;
            _facultyController = facultyController;
        }

        public override void Execute(object parameter)
        {
            _facultyController.Logout();
            _accountStore.Logout();
            _navigationService.Navigate();
        }
    }
}
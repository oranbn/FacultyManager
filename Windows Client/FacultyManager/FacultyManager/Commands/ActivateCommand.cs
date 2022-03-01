using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;

namespace FacultyManager.Commands
{
    public class ActivateCommand : CommandBase
    {
        private readonly AccountActivationViewModel _viewModel;
        private readonly INavigationService _navigationService;

        public ActivateCommand(AccountActivationViewModel viewModel,  INavigationService navigationService)
        {
            _viewModel = viewModel;
            _navigationService = navigationService;
        }

        public override void Execute(object parameter)
        {
            _viewModel.Activate();
        }
    }
}
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
    public class RegisterCommand : CommandBase
    {
        private readonly RegisterViewModel _viewModel;

        public RegisterCommand(RegisterViewModel viewModel)
        {
            _viewModel = viewModel;
        }

        public override void Execute(object parameter)
        {
            _viewModel.Register();
        }
    }
}
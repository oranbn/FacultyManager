using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.Commands
{
    public class NavigateCommand<TViewModel> : CommandBase
        where TViewModel : NotifiableObject
    {
        private readonly NavigationStore _navigationStore;
        private readonly Func<TViewModel> _createViewModel;

        public NavigateCommand(NavigationStore navigationStore, Func<TViewModel> createViewModel)
        {
            _navigationStore = navigationStore;
            _createViewModel = createViewModel;
        }

        public override void Execute(object parameter)
        {
            _navigationStore.CurrentViewModel = _createViewModel();
        }
    }
}
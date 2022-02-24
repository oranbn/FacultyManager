using FacultyManager.Stores;
using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.Service
{
    public class NavigationService<TViewModel> : INavigationService where TViewModel : NotifiableObject
    {
        private readonly NavigationStore _navigationStore;
        private readonly Func<TViewModel> _createViewModel;

        public NavigationService(NavigationStore navigationStore, Func<TViewModel> createViewModel)
        {
            _navigationStore = navigationStore;
            _createViewModel = createViewModel;
        }

        public void Navigate()
        {
            _navigationStore.CurrentViewModel = _createViewModel();
        }
    }
}
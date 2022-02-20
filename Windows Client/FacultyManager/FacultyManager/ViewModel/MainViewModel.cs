using FacultyManager.Stores;
using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.ViewModel
{
    public class MainViewModel : NotifiableObject
    {
        private readonly NavigationStore _navigationStore;

        public NotifiableObject CurrentViewModel => _navigationStore.CurrentViewModel;

        public MainViewModel(NavigationStore navigationStore)
        {
            _navigationStore = navigationStore;

            _navigationStore.CurrentViewModelChanged += OnCurrentViewModelChanged;
        }

        private void OnCurrentViewModelChanged()
        {
            RaisePropertyChanged(nameof(CurrentViewModel));
        }
    }
}
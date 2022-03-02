using FacultyManager.Stores;
using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.ViewModel
{
    public class MainViewModel : NotifiableObject
    {
        private readonly NavigationStore _navigationStore;
        private readonly ModalNavigationStore _modalNavigationStore;

        public NotifiableObject CurrentViewModel => _navigationStore.CurrentViewModel;
        public NotifiableObject CurrentModalViewModel => _modalNavigationStore.CurrentViewModel;
        public bool IsOpen => _modalNavigationStore.IsOpen;

        public MainViewModel(NavigationStore navigationStore, ModalNavigationStore modalNavigationStore)
        {
            _navigationStore = navigationStore;
            _modalNavigationStore = modalNavigationStore;

            _navigationStore.CurrentViewModelChanged += OnCurrentViewModelChanged;
            _modalNavigationStore.CurrentViewModelChanged += OnCurrentModalViewModelChanged;
        }

        private void OnCurrentViewModelChanged()
        {
            RaisePropertyChanged(nameof(CurrentViewModel));
        }

        private void OnCurrentModalViewModelChanged()
        {
            RaisePropertyChanged(nameof(CurrentModalViewModel));
            RaisePropertyChanged(nameof(IsOpen));
        }

        public override void Execute()
        {
            throw new NotImplementedException();
        }
    }
}
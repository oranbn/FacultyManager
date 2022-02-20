using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;

namespace FacultyManager.Stores
{
    public class NavigationStore
    {
        public event Action CurrentViewModelChanged;

        private NotifiableObject _currentViewModel;
        public NotifiableObject CurrentViewModel
        {
            get => _currentViewModel;
            set
            {
                _currentViewModel = value;
                OnCurrentViewModelChanged();
            }
        }

        private void OnCurrentViewModelChanged()
        {
            CurrentViewModelChanged?.Invoke();
        }
    }
}
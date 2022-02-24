using FacultyManager.ViewModel;
using System;
using System.Collections.Generic;
using System.Text;
using FacultyManager;

namespace FacultyManager.Stores
{
    public class ModalNavigationStore
    {
        private NotifiableObject _currentViewModel;
        public NotifiableObject CurrentViewModel
        {
            get => _currentViewModel;
            set
            {
                _currentViewModel?.Dispose();
                _currentViewModel = value;
                OnCurrentViewModelChanged();
            }
        }

        public bool IsOpen => CurrentViewModel != null;

        public event Action CurrentViewModelChanged;

        public void Close()
        {
            CurrentViewModel = null;
        }

        private void OnCurrentViewModelChanged()
        {
            CurrentViewModelChanged?.Invoke();
        }
    }
}
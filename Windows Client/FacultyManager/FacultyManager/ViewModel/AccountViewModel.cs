using FacultyManager.Commands;
using FacultyManager.Model;
using FacultyManager.Service;
using FacultyManager.Stores;
using System.Windows.Input;
using FacultyManager.ViewModel;

namespace FacultyManager.ViewModel
{
    public class AccountViewModel : NotifiableObject
    {
        private readonly AccountStore _accountStore;
        private readonly FacultyController _facultyController;
        public string Email => _accountStore.CurrentAccount?.Email;

        public AccountViewModel(FacultyController facultyController, AccountStore accountStore)
        {
            _facultyController = facultyController;
            _accountStore = accountStore;
            _accountStore.CurrentAccountChanged += OnCurrentAccountChanged;
        }

        private void OnCurrentAccountChanged()
        {
            RaisePropertyChanged(nameof(Email));
        }

        public override void Dispose()
        {
            _accountStore.CurrentAccountChanged -= OnCurrentAccountChanged;

            base.Dispose();
        }
    }
}
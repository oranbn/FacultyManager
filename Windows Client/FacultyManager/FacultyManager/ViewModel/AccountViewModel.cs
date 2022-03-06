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
        public string Email
        {
            get => _accountStore.CurrentAccount?.Email;
            set
            {
                _accountStore.CurrentAccount.Email = value;
                RaisePropertyChanged("Email");
            }
        }
        public string FirstName {
            get => _accountStore.CurrentAccount?.FirstName;
            set {
                _accountStore.CurrentAccount.FirstName = value;
                RaisePropertyChanged("FirstName");
            }
        }
        public string LastName {
            get => _accountStore.CurrentAccount?.LastName;
            set {
                _accountStore.CurrentAccount.LastName = value;
                RaisePropertyChanged("LastName");
            }
        }
        public string IdNumber {
            get => _accountStore.CurrentAccount?.IdNumber;
            set {
                _accountStore.CurrentAccount.IdNumber = value;
                RaisePropertyChanged("IdNumber");
            }
        }
        public string PhoneNumber {
            get => _accountStore.CurrentAccount?.PhoneNumber;
            set {
                _accountStore.CurrentAccount.PhoneNumber = value;
                RaisePropertyChanged("PhoneNumber");
            }
        }
        public string Birthday {
            get => _accountStore.CurrentAccount?.Birthday;
            set {
                _accountStore.CurrentAccount.Birthday = value;
                RaisePropertyChanged("PhoneNumber");
            }
        }
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

        public override void Execute(object parameter)
        {
            throw new System.NotImplementedException();
        }
    }
}
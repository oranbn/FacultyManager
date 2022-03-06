using FacultyManager.Commands;
using FacultyManager.Service;
using System.Windows.Input;
using FacultyManager.Model;
using FacultyManager.Model.Operations;
using FacultyManager.Model.Operations.ServerResponse;
using FacultyManager.Stores;

namespace FacultyManager.ViewModel
{
    public class RegisterViewModel : NotifiableObject
    {
        private ModalNavigationStore _modalNavigationStore;
        private INavigationService _homeNavigationService;
        private INavigationService _closeNavigationService;
        public ICommand NavigateLoginCommand { get; }
        public ICommand RegisterCommand { get; }
        
        private readonly FacultyController _facultyController;
        private string _email;
        public string Email
        {
            get => _email;
            set
            {
                this._email = value;
                RaisePropertyChanged("Email");
            }
        }
        private string _password;
        public string Password
        {
            get => _password;
            set
            {
                this._password = value;
                RaisePropertyChanged("Password");
            }
        }
        private string _firstName;
       public string FirstName
       {
           get => _firstName;
           set
           {
               this._firstName = value;
               RaisePropertyChanged("FirstName");
           }
       }
       private string _lastName;
       public string LastName
       {
           get => _lastName;
           set
           {
               this._lastName = value;
               RaisePropertyChanged("LastName");
           }
       }
       private string _idNumber;
       public string IdNumber
       {
           get => _idNumber;
           set
           {
               this._idNumber = value;
               RaisePropertyChanged("IdNumber");
           }
       }
       private string _phoneNumber;
       public string PhoneNumber
       {
           get => _phoneNumber;
           set
           {
               this._phoneNumber = value;
               RaisePropertyChanged("PhoneNumber");
           }
       }
       private string _birthday;
       public string Birthday
       {
           get => _birthday;
           set
           {
               this._birthday = value;
               RaisePropertyChanged("Birthday");
           }
       }
        public RegisterViewModel(FacultyController facultyController,ModalNavigationStore modalNavigationStore ,INavigationService homeNavigationService, INavigationService closeNavigationService)
        {
            RegisterCommand = new GenericCommand(this);
            _facultyController = facultyController;
            _modalNavigationStore = modalNavigationStore;
            _homeNavigationService = homeNavigationService;
            _closeNavigationService = closeNavigationService;
        }

        public void Register()
        {
            MessageResponse messageResponse = (MessageResponse) _facultyController.Register(Email, Password, FirstName,
                LastName, IdNumber, PhoneNumber, Birthday);
            switch (messageResponse.getOpCode()) {
                case 1:
                    new ModalNavigationService<PopupMessageViewModel>(
                        _modalNavigationStore,
                        () => new PopupMessageViewModel(messageResponse, _homeNavigationService)).Navigate();
                    break;
                case 2:
                    new ModalNavigationService<PopupMessageViewModel>(
                        _modalNavigationStore,
                        () => new PopupMessageViewModel(messageResponse, _closeNavigationService)).Navigate();
                    break;
            }
    }

        public override void Execute(object parameter)
        {
            Register();
        }
    }
}
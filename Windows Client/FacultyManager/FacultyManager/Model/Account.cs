namespace FacultyManager.Model
{
    public class Account
    {
        public string Email
        {
            get => email;
            set => email = value;
        }

        public string FirstName
        {
            get => firstName;
            set => firstName = value;
        }

        public string LastName
        {
            get => lastName;
            set => lastName = value;
        }

        public string IdNumber
        {
            get => idNumber;
            set => idNumber = value;
        }

        public string PhoneNumber
        {
            get => phoneNumber;
            set => phoneNumber = value;
        }

        public string Birthday
        {
            get => birthday;
            set => birthday = value;
        }

        private string email;
        private string firstName;
        private string lastName;
        private string idNumber;
        private string phoneNumber;
        private string birthday;
        public Account(string email, string firstName, string lastName, string idNumber, string phoneNumber, string birthday)
        {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.idNumber = idNumber;
            this.phoneNumber = phoneNumber;
            this.birthday = birthday;
        }

        
    }
}
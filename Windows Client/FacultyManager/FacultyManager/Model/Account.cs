namespace FacultyManager.Model
{
    public class Account
    {
        public string Email
        {
            get => email;
            set { email = value; }
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
        public bool IsApproved
        {
            get => isApproved;
            set => isApproved = value;
        }

        private string email;
        private string firstName;
        private string lastName;
        private string idNumber;
        private string phoneNumber;
        private string birthday;
        private bool isApproved;
        public Account(string email, string firstName, string lastName, string idNumber, string phoneNumber, string birthday, bool isApproved)
        {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.idNumber = idNumber;
            this.phoneNumber = phoneNumber;
            this.birthday = birthday;
            this.isApproved = isApproved;
        }

        
    }
}
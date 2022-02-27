namespace FacultyManager.Model.Operations.ServerResponse
{
    public class AccountResponse : ServerOperation
    {       
        public string Email => _email;

        public string FirstName => _firstName;

        public string LastName => _lastName;

        public string IdNumber => _idNumber;

        public string PhoneNumber => _phoneNumber;

        public string Birthday => _birthday;
        private string _email;
        private string _firstName;
        private string _lastName;
        private string _idNumber;
        private string _phoneNumber;
        private string _birthday;
        public AccountResponse(short opCode) : base(opCode)
        {
            _email = "";
            _firstName = "";
            _lastName = "";
            _idNumber = "";
            _phoneNumber = "";
            _birthday = "";
        }

        public override bool pushByte(byte nextByte)
        {
            if(nextByte == ';')
                return true;
            if(nextByte==(byte)0)
            {
                if(_email.Equals(""))
                    _email = bytesToString();
                else if(_firstName.Equals(""))
                    _firstName = bytesToString();
                else if(_lastName.Equals(""))
                    _lastName = bytesToString();
                else if(_idNumber.Equals(""))
                    _idNumber = bytesToString();
                else if(_phoneNumber.Equals(""))
                    _phoneNumber = bytesToString();
                else if(_birthday.Equals(""))
                    _birthday = bytesToString();
            }
            else
                pushNextByte(nextByte);
            return false;
        }
    }
}
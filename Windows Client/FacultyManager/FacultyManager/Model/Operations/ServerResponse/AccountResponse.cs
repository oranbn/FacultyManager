using System;

namespace FacultyManager.Model.Operations.ServerResponse
{
    public class AccountResponse : ServerOperation
    {       
        public string Email => _email;

        public string FirstName => _firstName;

        public string LastName => _lastName;

        public string IdNumber => _idNumber;

        public string PhoneNumber => _phoneNumber;
        public bool IsApproved => _isApproved;

        public string Birthday => _birthday;
        public int Permission => _permission;
        private string _email;
        private string _firstName;
        private string _lastName;
        private string _idNumber;
        private string _phoneNumber;
        private string _birthday;
        private int _permission;
        private bool _isApproved;
        public AccountResponse(short opCode) : base(opCode)
        {
            _email = "";
            _firstName = "";
            _lastName = "";
            _idNumber = "";
            _phoneNumber = "";
            _birthday = "";
            _permission = -1;
            _isApproved = false;
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
                else if (_permission == -1)
                    _permission = Int32.Parse(bytesToString());
                else
                    _isApproved = bytesToBoolean();
            }
            else
                pushNextByte(nextByte);
            return false;
        }
    }
}
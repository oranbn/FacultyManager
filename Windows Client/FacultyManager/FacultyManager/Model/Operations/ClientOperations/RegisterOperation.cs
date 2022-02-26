using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class RegisterOperation : ClientOperation
    {
        private readonly string _email;
        private readonly string _password;
        private readonly string _firstName;
        private readonly string _lastName;
        private readonly string _idNumber;
        private readonly string _phoneNumber;
        private readonly string _birthday;


        public RegisterOperation(short opCode, string email, string password, string firstName, string lastName, string idNumber, string phoneNumber, string birthday) : base(opCode)
        {
            _email = email;
            _password = password;
            _firstName = firstName;
            _lastName = lastName;
            _idNumber = idNumber;
            _phoneNumber = phoneNumber;
            _birthday = birthday;
        }

        public override byte[] encode()
        {
            byte[] bytes = new byte[_email.Length + _password.Length + _firstName.Length + _lastName.Length + _idNumber.Length + _phoneNumber.Length + _birthday.Length + 10];
            bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(_email, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_password, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_firstName, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_lastName, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_idNumber, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_phoneNumber, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(_birthday, bytes, ref index);
            bytes[index++] = 0;
            bytes[index] = (byte)';';
            return bytes;
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FacultyManager.Model.Operations.ClientOperations
{
    public class RegisterOperation : ClientOperation
    {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String idNumber;
        private String phoneNumber;
        private String birthday;

        public RegisterOperation(short opCode, string email, string password) : base(opCode)
        {
            this.email = email;
            this.password = password;
        }

        public override byte[] encode()
        {
            byte[] bytes = new byte[email.Length + password.Length+firstName.Length+lastName.Length+idNumber.Length+phoneNumber.Length+birthday.Length + 9];
            bytes[0] = (byte)((base.getOpCode() >> 8) & 0xFF);
            bytes[1] = (byte)(base.getOpCode() & 0xFF);
            int index = 2;
            AddStringToByteArray(email, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(password, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(firstName, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(lastName, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(idNumber, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(phoneNumber, bytes, ref index);
            bytes[index++] = 0;
            AddStringToByteArray(birthday, bytes, ref index);
            bytes[index++] = (byte)';';
            return bytes;
        }
    }
}

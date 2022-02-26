namespace FacultyManager.Model.Operations.ServerResponse
{
    public class MessageResponse : ServerOperation
    {
        private short messageOpCode;
        private string optional;
        public MessageResponse(short opCode) : base(opCode)
        {
            messageOpCode = -1;
        }
        public short getMessageOpCode() {
            return messageOpCode;
        }
        public string getOptional() {
            return optional;
        }

        public override bool pushByte(byte nextByte)
        {
            if(nextByte == ';')
                return true;
            if(nextByte=='\0')
            {
                if (messageOpCode == -1)
                    messageOpCode = bytesToShort();
                else
                    optional = bytesToString();
            }
            else
                pushNextByte(nextByte);
            return false;
        }
    }
}
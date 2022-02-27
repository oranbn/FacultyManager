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
            if (nextByte == ';')
                return true;
            if (messageOpCode == -1 && Length == 1) {
                pushNextByte(nextByte);
                messageOpCode = bytesToShort();
            }
            else if(nextByte=='\0' && messageOpCode!= -1)
            {
                optional = bytesToString();
            }
            else
                pushNextByte(nextByte);
            return false;
        }
    }
}
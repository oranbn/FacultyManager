using FacultyManager.Model.Operations;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using FacultyManager.Model.Operations.ServerResponse;

namespace FacultyManager.Model
{
    public class EncoderDecoder
    {
        int length = 0;
        private byte[] bytes = new byte[1 << 10];
        short opCode = -1;
        ServerOperation operation = null;
        public EncoderDecoder()
        {

        }
        public ServerOperation decodeNextByte(byte nextByte)
        {
            if (opCode == -1)
            {
                pushByte(nextByte);
                if (length == 2)
                {
                    opCode = (short)((bytes[0] & 0xff) << 8);
                    opCode += (short)(bytes[1] & 0xff);
                    switch (opCode)
                    {
                        case 1:
                            operation = new MessageResponse(opCode);
                            break;
                        case 2:
                            operation = new MessageResponse(opCode);
                            break;
                    }
                }
            }
            else
            {
                if(operation.pushByte(nextByte)) {
                    opCode = -1;
                    length = 0;
                    ServerOperation op = operation;
                    operation = null;
                    return op;
                }
            }
            return null;
        }
        public byte[] encode(ClientOperation message)
        {
            return message.encode();
        }
        private void pushByte(byte nextByte) {
            if (length >= bytes.Length) {
                Array.Resize(ref bytes, length * 2);
            }
            bytes[length++] = nextByte;
        }
    }
}
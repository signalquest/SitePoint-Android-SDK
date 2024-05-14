package com.signalquest.api.utils;

public class CircularByteBuffer {
    private byte[] buffer;
    private int head;
    private int tail;
    private int size;

    public CircularByteBuffer(int capacity) {
        buffer = new byte[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void put(byte[] bytes) {
        int bytesRemaining = bytes.length;
        int i = 0;

        while (bytesRemaining > 0) {
            int bytesToCopy = Math.min(bytesRemaining, buffer.length - tail);
            System.arraycopy(bytes, i, buffer, tail, bytesToCopy);
            bytesRemaining -= bytesToCopy;
            i += bytesToCopy;
            tail = (tail + bytesToCopy) % buffer.length;
            size += bytesToCopy;

            if (size > buffer.length) {
                head = (head + 1) % buffer.length;
                size--;
            }
        }
    }

//    public void put(byte[] bytes) {
//        for (byte b : bytes) {
//            buffer[tail] = b;
//            tail = (tail + 1) % buffer.length;
//            size++;
//            if (size > buffer.length) {
//                head = (head + 1) % buffer.length;
//                size--;
//            }
//        }
//    }

    public byte[] get(int maxLength) {
        byte[] bytes = new byte[Math.min(maxLength, size)];
        int bytesRead = 0;
        int bytesToCopy;

        while (bytesRead < bytes.length) {
            bytesToCopy = Math.min(bytes.length - bytesRead, buffer.length - head);
            System.arraycopy(buffer, head, bytes, bytesRead, bytesToCopy);
            bytesRead += bytesToCopy;
            head = (head + bytesToCopy) % buffer.length;
            size -= bytesToCopy;
        }

        return bytes;
    }

//    public byte[] get(int maxLength) {
//        byte[] bytes = new byte[Math.min(maxLength, size)];
//        int bytesRead = 0;
//
//        while (bytesRead < bytes.length && !isEmpty()) {
//            bytes[bytesRead++] = buffer[head];
//            head = (head + 1) % buffer.length;
//            size--;
//        }
//
//        return bytes;
//    }

    public boolean isFull() {
        return size == buffer.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
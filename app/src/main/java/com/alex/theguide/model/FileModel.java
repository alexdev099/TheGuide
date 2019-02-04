package com.alex.theguide.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileModel implements Parcelable {
    private long id;
    private String fileName;
    private boolean isFolder;
    private Date modDate;
    private FileType fileType;
    private boolean isOrange;
    private boolean isBlue;
    private long parentFolder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(boolean isFolder) {
        this.isFolder = isFolder;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public boolean isOrange() {
        return isOrange;
    }

    public void setOrange(boolean orange) {
        isOrange = orange;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    public void setParentFolder(long parentFolder) {
        this.parentFolder = parentFolder;
    }

    FileModel(){
    }

    private FileModel(Parcel in) {
        id = in.readLong();
        fileName = in.readString();
        isFolder = in.readByte() != 0;
        String date = in.readString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        try {
            modDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fileType = (FileType) in.readValue(FileType.class.getClassLoader());
        isOrange = in.readByte() != 0;
        isBlue = in.readByte() != 0;
        parentFolder = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(fileName);
        dest.writeByte((byte) (isFolder ? 1 : 0));
        String date = getModDate().toString();
        dest.writeString(date);
        dest.writeValue(fileType);
        dest.writeByte((byte) (isOrange ? 1 : 0));
        dest.writeByte((byte) (isBlue ? 1 : 0));
        dest.writeLong(parentFolder);
    }

    @SuppressWarnings("unused")
    public static final Creator<FileModel> CREATOR = new Creator<FileModel>() {
        @Override
        public FileModel createFromParcel(Parcel in) {
            return new FileModel(in);
        }

        @Override
        public FileModel[] newArray(int size) {
            return new FileModel[size];
        }
    };
}

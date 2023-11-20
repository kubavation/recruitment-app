package com.durys.jakub.recruitmentapp.registration.domain;

import java.util.Objects;

record Cv(String fileName, byte[] file) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cv cv = (Cv) o;
        return Objects.equals(fileName, cv.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }

    @Override
    public String toString() {
        return fileName;
    }
}

package com.example.dbms_app.model;

import com.google.gson.annotations.SerializedName;

public class Candidate {
    @SerializedName("NAME")
    String candidateName;
    @SerializedName("PARTY_NAME")
    String candidateParty;

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateParty() {
        return candidateParty;
    }

    public void setCandidateParty(String candidateParty) {
        this.candidateParty = candidateParty;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Candidate(String candidateName, String candidateParty, int numberOfVotes) {
        this.candidateName = candidateName;
        this.candidateParty = candidateParty;
        this.numberOfVotes = numberOfVotes;
    }

    @SerializedName("VOTES")
    int numberOfVotes;
}

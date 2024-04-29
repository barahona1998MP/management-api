package com.lawoffice.managementapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "date_meeting")
    private LocalDate dateMeeting;
    private TimeZone time;
    private String description;
    private Boolean status;
    @Column(name = "id_meeting_type")
    private Integer idMeetingType;
}

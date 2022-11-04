package com.notice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {

  private Integer noteId;
  private Integer memId;
  private java.sql.Timestamp noteTime;
  private String noteCont;
  private Integer noteStat;



}

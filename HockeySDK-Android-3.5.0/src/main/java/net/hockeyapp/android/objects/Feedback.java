package net.hockeyapp.android.objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h3>Description</h3>
 * 
 * Model for feedback threads.
 * 
 * <h3>License</h3>
 * 
 * <pre>
 * Copyright (c) 2011-2014 Bit Stadium GmbH
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * </pre>
 * 
 * @author Bogdan Nistor
 */
public class Feedback implements Serializable {
  private static final long serialVersionUID = 2590172806951065320L;
  
  private String name;
  private String email;
  private int id;
  private String createdAt;
  private ArrayList<FeedbackMessage> messages;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  
  public ArrayList<FeedbackMessage> getMessages() {
    return messages;
  }
  
  public void setMessages(ArrayList<FeedbackMessage> messages) {
    this.messages = messages;
  }
}

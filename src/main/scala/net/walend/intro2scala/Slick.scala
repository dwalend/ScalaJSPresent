package net.walend.intro2scala

import net.walend.present.{CodeBlock, CodeSyntax, LinkTextLine, SimpleSlide, Style, TextLine}
import net.walend.present.Shortcuts._


/**
 * @author dwalend
 * @since v0.1.2
 */
object Slick {

  val SlickIntro = SimpleSlide("Slick",
    t("Slick","http://slick.typesafe.com/doc/3.0.0/introduction.html"),
    TextLine("An FP Database Library For Scala", Style.HeadLine),
    TextLine("Uses Scala's Common Currencies In Its API",Style.SupportLine),
    TextLine("Case Classes and Tuples",Style.SupportLine),
    LinkTextLine("A Functional-Relational Mapping API Makes Queries Look Like Scala","http://slick.typesafe.com/doc/3.0.0/introduction.html",Style.HeadLine),
    LinkTextLine("Compact, Clean Code","https://open.med.harvard.edu/stash/projects/SHRINE/repos/shrine/browse/apps/steward-app/src/main/scala/net/shrine/steward/db/StewardDatabase.scala",Style.SupportLine),
    LinkTextLine("Composible Queries Run Inside Higher-Order Functions","https://open.med.harvard.edu/stash/projects/SHRINE/repos/shrine/browse/apps/steward-app/src/main/scala/net/shrine/steward/db/StewardDatabase.scala",Style.SupportLine),
    TextLine("A Plain SQL API Gives Full Control Over SQL",Style.HeadLine),
    TextLine("Isolates SQL's Complexity",Style.SupportLine)
  )

  val SlickLiftedTable = SimpleSlide("SlickLiftedTable",
    t("Slick Table"),
    CodeBlock("""case class TopicRecord(id:Option[TopicId] = None,
                |                        name:String,
                |                        description:String,
                |                        createdBy:UserName,
                |                        createDate:Date,
                |                        state:TopicState,
                |                        changedBy:UserName,
                |                        changeDate:Date) { ... }}
                |
                |  class TopicTable(tag:Tag) extends Table[TopicRecord](tag,"topics") {
                |    def id = column[TopicId]("id",O.PrimaryKey,O.AutoInc)
                |    def name = column[String]("name")
                |    def description = column[String]("description")
                |    def createdBy = column[UserName]("createdBy")
                |    def createDate = column[Date]("createDate")
                |    def state = column[TopicStateName]("state")
                |    def changedBy = column[UserName]("changedBy")
                |    def changeDate = column[Date]("changeDate")
                |
                |    def * = (id.?, name, description, createdBy, createDate, state, changedBy, changeDate) <> (fromRow, toRow) //(TopicRecord.tupled,TopicRecord.unapply)
                |
                |    def fromRow = (fromParams _).tupled
                |
                |    def fromParams(id:Option[TopicId] = None,
                |                   name:String,
                |                   description:String,
                |                   createdBy:UserName,
                |                   createDate:Date,
                |                   stateName:String,
                |                   changedBy:UserName,
                |                   changeDate:Date): TopicRecord = {
                |      TopicRecord(id, name, description, createdBy, createDate, TopicState.namesToStates(stateName), changedBy, changeDate)
                |    }
                |
                |    def toRow(topicRecord: TopicRecord) = Some((
                |        topicRecord.id,
                |        topicRecord.name,
                |        topicRecord.description,
                |        topicRecord.createdBy,
                |        topicRecord.createDate,
                |        topicRecord.state.name,
                |        topicRecord.changedBy,
                |        topicRecord.changeDate
                |        ))
                |  }
                |
                |  val allTopicQuery = TableQuery[TopicTable]
                |""".stripMargin)

  )

  val SlickLiftedQuery = SimpleSlide("SlickLiftedQuery",
    t("Slick Composible Query"),
    CodeBlock("""  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = allTopicQuery
                |    val researcherFilter = queryParameters.researcherIdOption.fold(allTopics)(
                |                       researcherId => allTopics.filter(_.createdBy === researcherId))
                |    val stateFilter = queryParameters.stateOption.fold(researcherFilter)(
                |                       state => researcherFilter.filter(_.state === state.name))
                |    val minDateFilter = queryParameters.minDate.fold(stateFilter)(
                |                       minDate => stateFilter.filter(_.changeDate >= minDate))
                |    val maxDateFilter = queryParameters.maxDate.fold(minDateFilter)(
                |                       maxDate => minDateFilter.filter(_.changeDate <= maxDate))
                |
                |    maxDateFilter
                |  }
                |
                |  private def topicSelectQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val countFilter = topicCountQuery(queryParameters)
                |
                |    //todo is there some way to get a map from column names to columns that I don't have to update?
                |    val orderByQuery = queryParameters.sortByOption.fold(countFilter)(
                |      columnName => countFilter.sortBy(
                |        x => queryParameters.sortOrder.orderForColumn(columnName match {
                |          case "id" => x.id
                |          case "name" => x.name
                |          case "description" => x.description
                |          case "createdBy" => x.createdBy
                |          case "createDate" => x.createDate
                |          case "state" => x.state
                |          case "changedBy" => x.changedBy
                |          case "changeDate" => x.changeDate
                |        })))
                |
                |      val skipFilter = queryParameters.skipOption.fold(orderByQuery)(
                |                      skip => orderByQuery.drop(skip))
                |      val limitFilter = queryParameters.limitOption.fold(skipFilter)(
                |                    limit => skipFilter.take(limit))
                |      limitFilter
                |    }
                |
                |    def selectTopicsForSteward(queryParameters: QueryParameters):StewardsTopics = {
                |
                |      val (count, topics, userNamesToOutboundUsers) = runTransactionBlocking {
                |        for {
                |          count <- topicCountQuery(queryParameters).length.result
                |          topics <- topicSelectQuery(queryParameters).result
                |          userNamesToOutboundUsers <- outboundUsersForNamesAction((topics.map(_.createdBy) ++ topics.map(_.changedBy)).to[Set])
                |        } yield (count, topics, userNamesToOutboundUsers)
                |      }
                |
                |    StewardsTopics(count,
                |                    queryParameters.skipOption.getOrElse(0),
                |                    topics.map(_.toOutboundTopic(userNamesToOutboundUsers)))
                |  }
                |
                |  def runTransactionBlocking[R](dbio: DBIOAction[R, NoStream, _], timeout: Duration = timeout): R = {
                |    try {
                |      Await.result(db.run(dbio.transactionally), timeout)
                |    } catch {
                |        case tx:TimeoutException => throw TimeoutInDbIoActionException(db.dataSource, timeout, tx)
                |        case NonFatal(x) => throw CouldNotRunDbIoActionException(db.dataSource, x)
                |      }
                |    }
                |  }""".stripMargin),
    TextLine("Produces SQL that looks like this: "),
    CodeBlock("""select x2."id", x2."name", x2."description", x2."createdBy",
                |x2."createDate", x2."state", x2."changedBy", x2."changeDate" from "topics"
                |x2 where x2."state" = 'Pending'
                |""".stripMargin,CodeSyntax.Sql)
  )

  val SlickComposibleQuery = SimpleSlide("SlickComposibleQuery",
    t("New Requirement - Change History for Topics"),
    TextLine("Compose With a New Query",Style.HeadLine),
    CodeBlock("""  val mostRecentTopicQuery: Query[TopicTable, TopicRecord, Seq] = for(
                |    topic <- allTopicQuery if !allTopicQuery.filter(_.id === topic.id).filter(_.changeDate > topic.changeDate).exists
                |  ) yield topic
                |
                |  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val allTopics = mostRecentTopicQuery
                |...""".stripMargin),
    TextLine("Now the SQL Looks Like This: "),
    CodeBlock("""select x2."id", x2."name", x2."description", x2."createdBy",
                |x2."createDate", x2."state", x2."changedBy", x2."changeDate" from "topics"
                |x2 where (not exists(select x3."createDate", x3."description", x3."state",
                |x3."changeDate", x3."changedBy", x3."id", x3."createdBy", x3."name" from
                |"topics" x3 where (x3."id" = x2."id")
                |and (x3."changeDate" > x2."changeDate"))) and (x2."state" = 'Pending')
                |""".stripMargin,CodeSyntax.Sql)
  )

  val SlickPlainSql = SimpleSlide("SlickPlainSql",
    t("Slick Plain SQL"),
    CodeBlock("""      val columnNamesToSelect = columnsToSelect.map(_.name)
                |
                |      //Get distinct researcher ids
                |      val distinctResearchersIdQuery = sql"select distinct(#$aniIdColumnName) from #$nodeTableName where #$roleColumnName = ${NodeType.RESEARCHER.abbreviation} or #$roleColumnName = ${NodeType.PHYSRES.abbreviation}".as[String]
                |      val researcherIds = distinctResearchersIdQuery.iterator
                |
                |      //Get all of the data for all of the distinct researchers, except the id (not distinct)
                |      val researchersQuery: StaticQuery[String, List[String]] = StaticQuery.query(s"select ${columnNamesToSelect.mkString(",")} from $nodeTableName where $aniIdColumnName = ?")
                |
                |      //Query for a list for each id
                |      val researchers = researcherIds.map(id => researchersQuery(id).first)
                |      //For each id, make a Map from column names to values
                |      val researcherMaps = researchers.map(researcher => columnNamesToSelect.zip(researcher).toMap)
                |
                |      //remove any null values from the map -- a bit of a hack
                |      val researcherMapsWithoutNulls = researcherMaps.map(researcher => researcher.filter(x => x._2 != null))
                |""".stripMargin)
  )

  //todo update
  val Slick3 = SimpleSlide("Slick3",
    t("Slick 3.0"),
    TextLine("Database I/O Actions Supports FP Cleanly",Style.HeadLine),
    TextLine("Publish to Akka Streams to get Reactive Backpressure",Style.SupportLine),
    LinkTextLine("Hikari NIO Connection Pool","http://brettwooldridge.github.io/HikariCP/",Style.SupportLine),
    TextLine("Macro-Based Implementation of the Plain SQL API",Style.HeadLine),
    TextLine("Compile-Time Checking and Type Inference For SQL Statements",Style.SupportLine),
    TextLine("Attempted Backwards Compatibility",Style.SupportLine)
  )

  val slides = Seq(SlickIntro,SlickLiftedTable,SlickLiftedQuery,SlickComposibleQuery,SlickPlainSql,Slick3)
}